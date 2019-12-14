/*
stage('ssh_waitForHost.groovy example') {
  environment {
    credentialID = <ID>
  }
  steps {
    script {
      Boolean isUp = ssh-waitForHost.groovy (
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
  
  int waitTime = defaults.waitTime ?: 10 // max time to wait in minutes before giving up
  int sleepTime = 30 // time in seconds
  
  String result = null
  Boolean status = true
  
  try {
    sleep (sleepTime) // lets not be too hasty here
    
    timeout (time: waitTime, unit: 'MINUTES') { // keep trying for this amount of time

      while (status == true) { // do we need to keep looping?
        status = false // be optimistic and assume this will be successful

        try {
          result = ssh(credentialID: credentialID, remoteHost: sshHost, cmdLine: 'uptime -s')
        }

        catch (def ssh_err) { // not able to connect
          status = true // nope, it wasnt successful.  Try again
          echo 'ssh_err='+ssh_err.getMessage()
          sleep (sleepTime)
        }
      }
      return True
    }
  }
  catch (def timeout_err) {
    echo 'timeout_err='+timeout_err.getMessage()
    throw timeout_err
  }
}
