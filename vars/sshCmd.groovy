//import org.asyla.somelibname

def call(Map stepParams = [:]) {
  def credentialID = stepParams.credentialID // unique identifier within Jenkins credental store
  def remoteHost = stepParams.remoteHost // DNS name or IP of the remote ssh server
  def CMD = stepParams.CMD ?: "hostname"

  withCredentials([sshUserPrivateKey(credentialsId: credentialID, keyFileVariable: 'keyFileLocation', passphraseVariable: '', usernameVariable: 'userID')]) {
    returnCode = sh(script: 'ssh ${userID}@'+remoteHost+' -i ${keyFileLocation} -o StrictHostKeyChecking=no '+CMD, returnStatus: true)
  }
}
