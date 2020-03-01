# Space-Invaders
Space invaders game written in Java using Processing's library.

The game looks and sounds like this: https://drive.google.com/file/d/1NXxpQJcsbDeq_EPKAS1WoO4OqqszejRD/view?usp=sharing


This game was made as a submission for an assignment for INFO1113 at USYD. JUnit testing was required for this submission. 
To run without testing use 

    gradle build -x test

Then use

    gradle run
    
    
Note for MacOS users: the Processing library doesn't work well with versions of JDK 10+. 

Download and install JDK 8 https://www.dev2qa.com/how-to-install-uninstall-multiple-java-versions-in-mac-os-by-home-brew-or-manually/

Then change the JDK version being used on your system 
https://stackoverflow.com/questions/21964709/how-to-set-or-change-the-default-java-jdk-version-on-os-x

Using gradle run should work fine after doing so.
