################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
./src/fileparser.cpp \
./src/module3.cpp \
./src/operand.cpp \
./src/parse.cpp \
./src/subexpression.cpp \
./src/symboltable.cpp \
./src/variable.cpp 

OBJS += \
./src/fileparser.o \
./src/module3.o \
./src/operand.o \
./src/parse.o \
./src/subexpression.o \
./src/symboltable.o \
./src/variable.o 

CPP_DEPS += \
./src/fileparser.d \
./src/module3.d \
./src/operand.d \
./src/parse.d \
./src/subexpression.d \
./src/symboltable.d \
./src/variable.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ./src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


