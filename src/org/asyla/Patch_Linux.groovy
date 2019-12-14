//src/org/asyla/Patch_Linux.groovy

/*
import org.asyla.Patch_Linux

Patch_Linux.build(this)
*/

package org.asyla

class Patch_Linux {

  public static def build(caller) {
    caller.node('lbl') {
      caller.stage('A') {
        // Do build stuff
        //caller.sh(..)
        echo "did something"
      }
    }
  }
}
