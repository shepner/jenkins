/*
stage('ssh example') {
  environment {
    credentialID = <ID>
  }
  steps {
    script {
      result = ssh(
        credentialID: credentialID,
        remoteHost: 'hostname',
        cmdLine: 'uptime'
      )
      echo result
    }
  }
}
*/

//import org.asyla.somelibname

def call(Map defaults = [:]) {
  
  String credentialID  = defaults.credentialID // unique identifier within Jenkins credental store
  String remoteHost = defaults.remoteHost // DNS name or IP of the remote ssh server
  String cmdLine = defaults.cmdLine ?: "hostname" // Command to remotely execute:  run "hostname" by default
  
  String cmdOutput = null // contents of STDOUT
  
  try {
    withCredentials([sshUserPrivateKey(credentialsId: credentialID, keyFileVariable: 'keyFileLocation', passphraseVariable: '', usernameVariable: 'userID')]) {

      try {
        cmdOutput = sh(
          script: 'ssh ${userID}@'+remoteHost+' -i ${keyFileLocation} -o StrictHostKeyChecking=no '+cmdLine,
          returnStdout: true
        ).trim()

      } catch (def sh_err) { // the ssh terminated abnormally
        echo 'sh error: '+sh_err.getMessage()
        echo 'cmdLine='+cmdLine
        return
      }
    }
  } catch (def err) { // something went wrong fetching credentials
    echo 'error: '+err.getMessage()
    echo 'credentialID'+credentialID
    echo 'remoteHost='+remoteHost
    return
  }
  
  return cmdOutput
}
