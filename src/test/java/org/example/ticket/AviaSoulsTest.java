package org.example.ticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


public class AviaSoulsTest {
    AviaSouls aviaSouls = new AviaSouls();
    Ticket ticket1 = new Ticket("Москва", "Сочи",
            5_000, 12_00, 13_45);
    Ticket ticket2 = new Ticket("Москва", "Сочи",
            5_000, 10_30, 14_45);
    Ticket ticket3 = new Ticket("Москва", "Сочи",
            6_000, 9_00, 11_30);
    Ticket ticket4 = new Ticket("Москва", "Сочи",
            25_000, 8_30, 18_00);
    Ticket ticket5 = new Ticket("Москва", "Сочи",
            4_500, 10_00, 14_45);

    @BeforeEach
    public void setup() {
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
    }

    @Test
    public void testSearchAllTicket() {

        Ticket[] expected = {ticket5, ticket1, ticket2, ticket3, ticket4};
        Ticket[] actual = aviaSouls.search("Москва", "Сочи");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearch0Ticket() {
        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("Москва", "Казань");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testComparatorForMaxPrice() {
        Assertions.assertEquals(1, ticket4.compareTo(ticket1));
    }

    @Test
    public void testComparatorForMinPrice() {
        Assertions.assertEquals(-1, ticket5.compareTo(ticket3));
    }

    @Test
    public void testComparatorForEqualsPrice() {
        Assertions.assertEquals(0, ticket1.compareTo(ticket2));

    }

    @Test
    public void testComparatorForFlightTimeInAscendingOrder() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] expected = {ticket1, ticket3, ticket2, ticket5, ticket4};
        Ticket[] actual = aviaSouls.searchAndSortBy(
                "Москва", "Сочи", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testComparatorForFlightTimeCityIsAbsent() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.searchAndSortBy(
                "Новосибирск", "Краснодар", timeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}

