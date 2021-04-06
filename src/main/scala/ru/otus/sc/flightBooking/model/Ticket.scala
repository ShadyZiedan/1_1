package ru.otus.sc.flightBooking.model

import java.util.UUID

case class Ticket(id: UUID, flightId: Int, passengersCount: Int)
