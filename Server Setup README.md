# conductor-java-client-impl

download 'conductor-master' as zip from https://github.com/madhureddy480/conductor-server-ui-cloned.git and unzip it.

```
cd downloads
madhusudhans-MacBook-Pro:downloads madhusudhankarnati$ cd conductor-master
madhusudhans-MacBook-Pro:conductor-master madhusudhankarnati$ cd server
madhusudhans-MacBook-Pro:server madhusudhankarnati$ ../gradlew server
```

server will start and available for communication at: localhost:8080

in a new terminal

```
madhusudhans-MacBook-Pro:ui madhusudhankarnati$ cd ui
madhusudhans-MacBook-Pro:ui madhusudhankarnati$ sudo npm install -g gulp
#incase Babel errors execute like below 'natives' command. 
      madhusudhans-MacBook-Pro:ui madhusudhankarnati$ sudo npm install -g server
      madhusudhans-MacBook-Pro:ui madhusudhankarnati$ sudo npm i natives
# on last command to get Conductor UI up
madhusudhans-MacBook-Pro:ui madhusudhankarnati$ sudo gulp watch
```

Now the UI is serving at localhost:3000


Happy Development :)

