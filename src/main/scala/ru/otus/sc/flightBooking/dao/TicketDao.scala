package ru.otus.sc.flightBooking.dao

import ru.otus.sc.flightBooking.model.{Flight, Ticket}

import java.util.UUID

trait TicketDao {
  /**
   * Get's a ticket by id
   * @param id
   * @return
   */
  def getTicketById(id: UUID): Option[Ticket]

  /**
   * creates a ticket
   * @param flight
   * @param passengersCount
   * @return
   */
  def createTicket(flight: Flight, passengersCount: Int): Option[Ticket]

  /**
   * cancels or deletes a ticket
   * @param id
   * @return
   */
  def cancelTicket(id: UUID): Boolean

  /**
   * Gets passengers count for a flight by flight id
   * @param flightId
   * @return
   */
  def getCountPassengersForFlight(flightId: Int): Int
}
