package ru.otus.sc.flightBooking.model

import java.time.LocalDate
import java.util.Date

case class Flight(id: Int, flightNumber: String, date: LocalDate, origin: String, destination: String, seatsCapacity: Int)
