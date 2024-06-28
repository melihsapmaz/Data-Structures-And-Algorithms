JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		com/GTU/InventorySystem/model/Inventory.java \
		com/GTU/InventorySystem/model/Device.java \
		com/GTU/InventorySystem/main/Main.java \
		com/GTU/InventorySystem/model/devices/Headphones.java \
		com/GTU/InventorySystem/model/devices/Laptop.java \
		com/GTU/InventorySystem/model/devices/Smartphone.java \
		com/GTU/InventorySystem/model/devices/Smartwatch.java \
		com/GTU/InventorySystem/model/devices/TV.java \

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