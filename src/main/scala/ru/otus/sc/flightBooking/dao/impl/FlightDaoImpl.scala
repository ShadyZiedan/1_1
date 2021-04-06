package ru.otus.sc.flightBooking.dao.impl

import ru.otus.sc.flightBooking.dao.FlightDao
import ru.otus.sc.flightBooking.model.Flight

import java.time.LocalDate

class FlightDaoImpl extends FlightDao {
  private var flights: Map[Int, Flight] = Map()
  def createFlight(flightNumber: String, date: LocalDate, origin: String, destination: String, seatsCapacity: Int): Flight = {
    val flight = Flight(
      id = flights.size + 1,
      flightNumber = flightNumber,
      date = date,
      origin = origin,
      destination= destination,
      seatsCapacity = seatsCapacity)
    flights += (flight.id -> flight)

    flight
  }

  def searchFlights(date: LocalDate, origin: String, destination: String): List[Flight] = {
    flights.filter(t => t._2.origin == origin && t._2.destination == destination).values.toList
  }

  def getFlightByNumberAndDate(flightNumber: String, date: LocalDate): Option[Flight] = {
    flights.values.collectFirst {
      case f if f.flightNumber == flightNumber && f.date == date => f
    }
  }
}
