/*
stage('ssh example') {
  environment {
    credentialID = <ID>
  }
  steps {
    script {
      result = ssh(
        credentialID: credentialID,
        remoteHost: 'hostname'
      )
      echo result
    }
  }
}
*/

//import org.asyla.somelibname

@Library('asyla') _

def call(Map defaults = [:]) {
  
  String credentialID  = defaults.credentialID // unique identifier within Jenkins credental store
  String remoteHost = defaults.remoteHost // DNS name or IP of the remote ssh server
  
  String cmdOutput = null // contents of STDOUT
  
  String result = null
                    
  try {
      result = ssh(
          credentialID: credentialID,
          remoteHost: remoteHost,
          cmdLine: 'sudo reboot'
      )
  } catch (def err) {
      echo "ssh session terminated abnormally (as expected)"
  }

  throw 0
}
