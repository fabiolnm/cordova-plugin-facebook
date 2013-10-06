# Cordova Facebook Plugin for Android

## Development notes
There is no public Maven repository containing Cordova artifact, thus it's necessary install it manually on local repository:

	mvn install:install-file -DgroupId=org.apache -DartifactId=cordova -Dversion=3.1.0 -Dfile=/path/to/cordova.jar -Dpackaging=jar -DgeneratePom=true
	
where cordova.jar can be found at ```platforms/android/libs``` of a Cordova project.