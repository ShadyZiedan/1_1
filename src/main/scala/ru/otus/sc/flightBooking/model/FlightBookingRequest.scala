package ru.otus.sc.flightBooking.model

import java.time.LocalDate
import java.util.Date

case class FlightBookingRequest(flightNumber: String, date: LocalDate, passengersCount: Int)
