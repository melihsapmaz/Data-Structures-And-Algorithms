JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java Person.java SocialNetworkGraph.java


default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java $(TARGET)


clean:
	rm -f *.class

.PHONY: clean default