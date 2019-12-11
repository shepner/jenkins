/*
ssh()
*/

//import org.asyla.somelibname

def call(Map stepParams = [:]) {
  
  def credentialID = stepParams.credentialID // unique identifier within Jenkins credental store
  def remoteHost = stepParams.remoteHost // DNS name or IP of the remote ssh server
  def cmdLine = stepParams.cmdLine ?: "hostname" // Command to remotely execute:  run "hostname" by default

  withCredentials([sshUserPrivateKey(credentialsId: credentialID, keyFileVariable: 'keyFileLocation', passphraseVariable: '', usernameVariable: 'userID')]) {
    
    try {
      cmdOutput = sh(
        script: 'ssh ${userID}@'+remoteHost+' -i ${keyFileLocation} -o StrictHostKeyChecking=no '+cmdLine,
        returnStdout: true
      ).trim()
      
    } catch (Exception ex) {
      return ("ERROR: ${ex}")
      
    }
    return cmdOutput
  }
}
