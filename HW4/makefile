JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -private -splitindex -d ./javadoc
RM = rm
JR = java

CLASSES = \
		FileSystem/Directory.java \
		FileSystem/File.java \
		FileSystem/FileSystem.java \
		FileSystem/FileSystemElement.java \
		FileSystem/Main.java

all : Main.class

run :
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java

clean:
	$(RM) *.class

cleandoc:
	$(RM) -r ./javadoc