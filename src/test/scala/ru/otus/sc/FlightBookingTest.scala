package ru.otus.sc

import org.scalatest.FunSuite
import ru.otus.sc.flightBooking.dao.impl.{FlightDaoImpl, TicketDaoImpl}
import ru.otus.sc.flightBooking.model.{CancelBookingRequest, FlightBookingRequest}
import ru.otus.sc.flightBooking.service.FlightBookingServiceImpl

import java.time.LocalDate

class FlightBookingTest extends FunSuite {
  val flightDao = new FlightDaoImpl
  val ticketDao = new TicketDaoImpl
  val service = new FlightBookingServiceImpl(flightDao, ticketDao)

  test("create flight") {
    val flight = flightDao.createFlight("SU1234", LocalDate.of(2021, 4, 1), "Kazan", "Moscow", 300)
    assert(flight.id == 1)
  }

  test("create return flight") {
    val flight2 = flightDao.createFlight("SU1235", LocalDate.of(2021, 4, 1), "Moscow", "Kazan", 300)
    assert(flight2.id == 2)
  }

  test("find created flight") {
    assert(flightDao.getFlightByNumberAndDate("SU1235", LocalDate.of(2021, 4, 1)).isDefined)
  }

  test("is ticket reserved?") {
    val flightBookingRequest = FlightBookingRequest("SU1234", LocalDate.of(2021,4,1), 4)
    val response = service.book(flightBookingRequest)
    assert(response.ticket.isDefined)
  }

  test("is passengers count updated") {
    assert(ticketDao.getCountPassengersForFlight(1) == 4)
  }

  test("shouldn't overbook a flight") {
    val flightBookingRequest = FlightBookingRequest("SU1234", LocalDate.of(2021,4,1), 297)
    val response = service.book(flightBookingRequest)
    assert(response.ticket.isEmpty)
  }

  test("canceling a ticket") {
    val flightBookingRequest = FlightBookingRequest("SU1234", LocalDate.of(2021,4,1), 3)
    val response = service.book(flightBookingRequest)

    val cancelBookingResponse = service.cancelBooking(CancelBookingRequest(response.ticket.get.id))
    assert(cancelBookingResponse.success)
    assert(ticketDao.getTicketById(response.ticket.get.id).isEmpty)
  }
}