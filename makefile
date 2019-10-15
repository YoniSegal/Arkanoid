
#342476611
#segalyo2

compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*/*.java src/*.java
run:
	java -cp biuoop-1.4.jar:bin:resources Ass6Game
jar:
	jar -cmf Manifest.mf ass6game.jar -C bin . -C resources .
bin:
	mkdir bin

