/*
stage('ssh example') {
  environment {
    credentialID = <ID>
  }
  steps {
    script {
      ssh(
        credentialID: credentialID,
        remoteHost: 'hostname',
        cmdLine: 'uptime'
      )
    }
  }
}
*/

//import org.asyla.somelibname

def call(Map stepParams = [:]) {
  
  def credentialID // unique identifier within Jenkins credental store
  def remoteHost // DNS name or IP of the remote ssh server
  def cmdLine = stepParams.cmdLine ?: "hostname" // Command to remotely execute:  run "hostname" by default
  
  withCredentials([sshUserPrivateKey(credentialsId: credentialID, keyFileVariable: 'keyFileLocation', passphraseVariable: '', usernameVariable: 'userID')]) {
    
    try {
      echo "about to run ssh"
      cmdOutput = sh(
        script: 'ssh ${userID}@'+remoteHost+' -i ${keyFileLocation} -o StrictHostKeyChecking=no '+cmdLine,
        returnStdout: true
      ).trim()
      echo "ssh done"
    } catch (err) { return err.getMessage() }
    echo cmdOutput
    return cmdOutput
  }
}