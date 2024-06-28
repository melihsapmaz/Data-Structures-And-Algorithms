SRC_DIR = src/FileSystem
OUT_DIR = out
JCFLAGS := -d $(OUT_DIR)/ -cp $(SRC_DIR)/

SRCS := $(wildcard $(SRC_DIR)/*.java)
CLS := $(SRCS:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

.SUFFIXES: .java

.PHONY: all clean

all: $(CLS)

$(CLS): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java
    $(JC) $(JCFLAGS) $<

clean:
    rm $(OUT_DIR)/*.class