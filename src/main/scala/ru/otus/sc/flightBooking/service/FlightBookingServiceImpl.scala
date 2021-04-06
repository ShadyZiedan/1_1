package ru.otus.sc.flightBooking.service

import ru.otus.sc.flightBooking.dao.{FlightDao, TicketDao}
import ru.otus.sc.flightBooking.model.{CancelBookingRequest, CancelBookingResponse, FlightBookingRequest, FlightBookingResponse, Ticket}

class FlightBookingServiceImpl(flightDao: FlightDao, ticketDao: TicketDao) extends FlightBookingService {
  def book(request: FlightBookingRequest): FlightBookingResponse = {
    val flight = flightDao.getFlightByNumberAndDate(request.flightNumber, request.date)
    flight match {
      case Some(f) => FlightBookingResponse(ticketDao.createTicket(f, request.passengersCount))
      case None => FlightBookingResponse(None)
    }

  }

  def cancelBooking(request: CancelBookingRequest): CancelBookingResponse = {
      CancelBookingResponse(ticketDao.cancelTicket(request.ticketId))
  }
}
