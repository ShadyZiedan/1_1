package ru.otus.sc

import ru.otus.sc.flightBooking.dao.impl.{FlightDaoImpl, TicketDaoImpl}
import ru.otus.sc.flightBooking.model.{CancelBookingRequest, CancelBookingResponse, FlightBookingRequest, FlightBookingResponse}
import ru.otus.sc.flightBooking.service.{FlightBookingService, FlightBookingServiceImpl}
import ru.otus.sc.greet.dao.impl.GreetingDaoImpl
import ru.otus.sc.greet.model.{GreetRequest, GreetResponse}
import ru.otus.sc.greet.service.GreetingService
import ru.otus.sc.greet.service.impl.GreetingServiceImpl

trait App {
  def greet(request: GreetRequest): GreetResponse

  def bookFlight(request: FlightBookingRequest): FlightBookingResponse
  def cancelFlight(request: CancelBookingRequest): CancelBookingResponse
}

object App {
  private class AppImpl(greeting: GreetingService, flightBookingService: FlightBookingService) extends App {
    def greet(request: GreetRequest): GreetResponse = greeting.greet(request)

    def bookFlight(request: FlightBookingRequest): FlightBookingResponse = flightBookingService.book(request)
    def cancelFlight(request: CancelBookingRequest): CancelBookingResponse = flightBookingService.cancelBooking(request)
  }

  def apply(): App = {
    val greetingDao     = new GreetingDaoImpl
    val greetingService = new GreetingServiceImpl(greetingDao)

    val flightBookingService = new FlightBookingServiceImpl(new FlightDaoImpl, new TicketDaoImpl)
    new AppImpl(greetingService, flightBookingService = flightBookingService)
  }
}
