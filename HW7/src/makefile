JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java AVLTree.java GUIVisualization.java InputFileGenerator.java Stock.java StockDataManager.java


default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java $(TARGET)


clean:
	rm -f *.class

.PHONY: clean default