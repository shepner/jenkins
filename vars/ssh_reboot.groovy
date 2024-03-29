/*
stage('ssh_reboot example') {
  environment {
    credentialID = <ID>
  }
  steps {
    script {
      ssh_reboot (
        credentialID: credentialID,
        remoteHost: 'hostname'
      )
    }
  }
}
*/

//import org.asyla.somelibname

def call(Map defaults = [:]) {
  
  String credentialID  = defaults.credentialID // unique identifier within Jenkins credental store
  String remoteHost = defaults.remoteHost // DNS name or IP of the remote ssh server
  
  String result = null
                    
  try {
      ssh (credentialID: credentialID, remoteHost: remoteHost, cmdLine: 'sudo reboot')
  } catch (def err) {
      echo "ssh session terminated abnormally (as expected)"
  }

  return "`ssh_reboot()` complete"
}
