package ru.otus.sc.flightBooking.dao

import ru.otus.sc.flightBooking.model.Flight

import java.time.LocalDate
import java.util.Date

trait FlightDao {

  /**
   * creates a flight
   * @param flightNumber
   * @param planDate
   * @param origin
   * @param destination
   * @param seatsCapacity
   * @return
   */
  def createFlight(flightNumber: String, planDate: LocalDate, origin: String, destination: String, seatsCapacity: Int): Flight

  /**
   * Searches a flight by date, origin, and destination
   * @param date
   * @param origin
   * @param destination
   * @return
   */
  def searchFlights(date: LocalDate, origin: String, destination: String): List[Flight]

  /**
   * Gets a flight by flightNumber and date
   * @param flightNumber
   * @param date
   * @return
   */
  def getFlightByNumberAndDate(flightNumber: String, date: LocalDate): Option[Flight]
}
