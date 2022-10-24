package data.structures;
import data.structures.implementations.Fila;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FilaTest {

    @Test
    void initializationOk() {
        //given
        int size = random(10);

        //when
        data.structures.interfaces.Fila<Integer> fila = new Fila<>(size);

        //then
        assertThat(fila.size()).isEqualTo(size);
        assertThat(fila.isEmpty()).isTrue();
        assertThat(fila.front()).isNull();
        assertThat(fila.rear()).isNull();
    }

    @Test
    void initializationZeroSizeNotAllowed() {
        //given when then
        assertThatThrownBy(() -> new Fila<>(0))
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void initializationNegativeSizeNotAllowed() {
        //given
        final int size = -random(10);

        //when then
        assertThatThrownBy(() -> new Fila<>(size))
                .isInstanceOf(RuntimeException.class) ;
    }

    @ParameterizedTest
    @MethodSource("enqueueArguments")
    void enqueueOK(int size, int [] elements, int expectedRear, int expectedFront, boolean expectedFull) {
        //given
        data.structures.interfaces.Fila<Integer> fila = new Fila<>(size);

        //when
        for(var element: elements) fila.enqueue(element);

        //then
        assertThat(fila.rear()).isEqualTo(expectedRear);
        assertThat(fila.front()).isEqualTo(expectedFront);
        assertThat(fila.isFull()).isEqualTo(expectedFull);
        assertThat(fila.isEmpty()).isFalse();
    }

    static Stream<Arguments> enqueueArguments() {
        int elementOne = random();
        int elementTwo = random();

        return Stream.of(
                arguments(2, new int[] {elementOne, elementTwo}, elementTwo, elementOne, true),
                arguments(3, new int[] {elementOne, elementTwo}, elementTwo, elementOne, false),
                arguments(1, new int[] {elementOne}, elementOne, elementOne, true),
                arguments(2, new int[] {elementOne}, elementOne, elementOne, false)
        );
    }

    @Test
    void enqueueFailedFullStack() {
        //given
        int elementOne = random();
        int elementTwo = random();
        data.structures.interfaces.Fila<Integer> fila = new Fila<>(1);
        fila.enqueue(elementOne);

        //when then
        assertThatThrownBy(() -> fila.enqueue(elementTwo))
                .isInstanceOf(RuntimeException.class) ;
    }

    @ParameterizedTest
    @MethodSource("dequeueArguments")
    void dequeueOk(data.structures.interfaces.Fila<Integer> filaToBeUsed, int expectedElement, boolean expectedEmpty) {
        //given when
        var actualElement = filaToBeUsed.dequeue();

        //then
        assertThat(actualElement).isEqualTo(expectedElement);
        assertThat(filaToBeUsed.isEmpty()).isEqualTo(expectedEmpty);
        assertThat(filaToBeUsed.isFull()).isFalse();
    }

    static Stream<Arguments> dequeueArguments() {
        int elementOne = random();
        int elementTwo = random();

        data.structures.interfaces.Fila<Integer> filaOneElementFull = new Fila<>(1);
        filaOneElementFull.enqueue(elementOne);

        data.structures.interfaces.Fila<Integer> filaTwoElementsFull = new Fila<>(2);
        filaTwoElementsFull.enqueue(elementOne);
        filaTwoElementsFull.enqueue(elementTwo);

        data.structures.interfaces.Fila<Integer> filaTwoElementsNotFull = new Fila<>(2);
        filaTwoElementsNotFull.enqueue(elementOne);

        return Stream.of(
                arguments(filaOneElementFull, elementOne, true),
                arguments(filaTwoElementsFull, elementOne, false),
                arguments(filaTwoElementsNotFull, elementOne, true)
        );
    }

    @Test
    void dequeueFailedEmptyQueue() {
        //given
        int size = random(10);
        data.structures.interfaces.Fila<Integer> fila = new Fila<>(size);

        //when then
        assertThatThrownBy(fila::dequeue)
                .isInstanceOf(RuntimeException.class) ;
    }

    @Test
    void verifyQueue() {
        //given
        int elementOne = random();
        int elementTwo = random();
        int elementThree = random();

        //when then
        data.structures.interfaces.Fila<Integer> fila = new Fila<>(3);
        assertThat(fila.isEmpty()).isTrue();
        assertThat(fila.isFull()).isFalse();
        assertThat(fila.front()).isNull();
        assertThat(fila.rear()).isNull();

        fila.enqueue(elementOne);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();
        assertThat(fila.front()).isEqualTo(elementOne);
        assertThat(fila.rear()).isEqualTo(elementOne);

        fila.enqueue(elementTwo);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();
        assertThat(fila.front()).isEqualTo(elementOne);
        assertThat(fila.rear()).isEqualTo(elementTwo);

        fila.enqueue(elementThree);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isTrue();
        assertThat(fila.rear()).isEqualTo(elementThree);
        assertThat(fila.front()).isEqualTo(elementOne);

        assertThat(fila.dequeue()).isEqualTo(elementOne);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();

        assertThat(fila.dequeue()).isEqualTo(elementTwo);
        assertThat(fila.isEmpty()).isFalse();
        assertThat(fila.isFull()).isFalse();

        assertThat(fila.getIndexFront()).isEqualTo(2);
        assertThat(fila.getIndexRear()).isEqualTo(2);
        assertThat(fila.dequeue()).isEqualTo(elementThree);
        assertThat(fila.getIndexFront()).isEqualTo(-1);
        assertThat(fila.getIndexRear()).isEqualTo(-1);
        assertThat(fila.isEmpty()).isTrue();
        assertThat(fila.isFull()).isFalse();
    }

    private static int random() {
        return new Random().nextInt();
    }

    private static int random(int limit) {
        var number = 0;
        while (number == 0) {
            number = new Random().nextInt(limit+1);
        }
        return number;
    }
}