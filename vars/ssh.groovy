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
  
  def credentialID  = defaults.credentialID // unique identifier within Jenkins credental store
  def remoteHost = defaults.remoteHost // DNS name or IP of the remote ssh server
  def cmdLine = defaults.cmdLine ?: "hostname" // Command to remotely execute:  run "hostname" by default
  
  // echo credentialID
  // echo remoteHost
  // echo cmdLine
  
  try {
    withCredentials([sshUserPrivateKey(credentialsId: credentialID, keyFileVariable: 'keyFileLocation', passphraseVariable: '', usernameVariable: 'userID')]) {

      cmdOutput = sh(
        script: 'ssh ${userID}@'+remoteHost+' -i ${keyFileLocation} -o StrictHostKeyChecking=no '+cmdLine,
        returnStdout: true
      ).trim()

      echo "cmdOutput="+cmdOutput
      // Error example: '`script returned exit code 255`'
      if (cmdOutput ==~ 'returned.*' ) {
        echo 'ERROR ERROR ERROR ERROR'
      } else {
        return cmdOutput
      }

    }
  } catch (err) { 
    // Note: when using `returnStdout` in `sh()`, you dont get the exit code in a way that works with `try{} catch{}`
    return err.getMessage() 
  }
}
