package ru.otus.sc.flightBooking.service

import ru.otus.sc.flightBooking.model.{CancelBookingRequest, CancelBookingResponse, FlightBookingRequest, FlightBookingResponse}

/**
 * A service for reserving or cancelling flight tickets
 */
trait FlightBookingService {
  /**
   * books a flight
   * @param request
   * @return
   */
  def book(request: FlightBookingRequest): FlightBookingResponse

  /**
   * cancels a booking
   * @param request
   * @return
   */
  def cancelBooking(request: CancelBookingRequest): CancelBookingResponse
}
