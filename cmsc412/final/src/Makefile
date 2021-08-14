scalf-final: build/scalf-final.o
	g++ build/scalf-final.o -o scalf-final $(LDFLAGS)

build/scalf-final.o: scalf-final.cpp
	mkdir -p build
	g++ -MMD -MP -std=c++11 -c scalf-final.cpp -o build/scalf-final.o

.PHONY: clean

clean:
	rm -r build scalf-final
