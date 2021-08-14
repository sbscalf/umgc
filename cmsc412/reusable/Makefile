# Modified from makefiletutorial.com
TARGET_EXEC := scalf-hw2

BUILD_DIR := ./build
SRC_DIRS := ./src

SRC_FILES := $(shell find $(SRC_DIRS) -name *.c)
OBJS := $(SRC_FILES:%.c=$(BUILD_DIR)/%.o)
DEPS := $(OBJS:.o=.d)

INC_DIRS := $(shell find $(SRC_DIRS) -type d)
INC_FLAGS := $(addprefix -I,$(INC_DIRS))

CC := gcc
CFLAGS := $(INC_FLAGS) -MMD -MP

$(TARGET_EXEC): $(OBJS)
	$(CC) $(OBJS) -o $@ $(LDFLAGS)

$(BUILD_DIR)/%.o: %.c
	mkdir -p $(dir $@)
	$(CC) $(CFLAGS) -c $< -o $@

.PHONY: clean
clean:
	rm -rf $(BUILD_DIR)
	rm -f $(TARGET_EXEC)

cleanbuild:
	rm -rf $(BUILD_DIR)

-include $(DEPS)
