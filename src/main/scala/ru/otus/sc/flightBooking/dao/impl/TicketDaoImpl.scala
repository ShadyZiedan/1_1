package ru.otus.sc.flightBooking.dao.impl

import ru.otus.sc.flightBooking.dao.TicketDao
import ru.otus.sc.flightBooking.model.{Flight, Ticket}

import java.util.UUID

class TicketDaoImpl extends TicketDao {
  private var tickets: Map[UUID, Ticket] = Map()

  def getTicketById(id: UUID): Option[Ticket] = tickets.get(id)

  def createTicket(flight: Flight, passengersCount: Int): Option[Ticket] = {
    if (getCountPassengersForFlight(flight.id) + passengersCount > flight.seatsCapacity) {
      None
    } else {
      val ticket = Ticket(id = UUID.randomUUID(), flight.id, passengersCount)
      tickets += (ticket.id -> ticket)
      Some(ticket)
    }
  }

  def cancelTicket(id: UUID): Boolean = getTicketById(id) match {
    case Some(_) =>
      tickets -= id
      true
    case None => false
  }

  def getCountPassengersForFlight(flightId: Int): Int = {
    tickets.filter(t => t._2.flightId == flightId).values.map(ticket => ticket.passengersCount).sum
  }

}
