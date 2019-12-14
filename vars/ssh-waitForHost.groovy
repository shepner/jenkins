/*
stage('ssh-waitForHost.groovy example') {
  environment {
    credentialID = <ID>
  }
  steps {
    script {
      ssh_reboot (
        credentialID: credentialID,
        remoteHost: 'hostname'
        waitTime: minutesToWait
      )
    }
  }
}
*/

//import org.asyla.somelibname

def call(Map defaults = [:]) {
  
  String credentialID  = defaults.credentialID // unique identifier within Jenkins credental store
  String remoteHost = defaults.remoteHost // DNS name or IP of the remote ssh server
  String waitTime = defaults.waitTime ?: 10 // time in minutes
  
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

  return "`ssh_reboot()` complete"
}
