package com.arrendadora.service;

import com.arrendadora.model.Reserva;
import com.arrendadora.repository.reservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final reservaRepository reservaRepository;

    @Autowired
    public ReservaService(reservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    // Guardar una reserva
    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Obtener todas las reservas
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    // Obtener una reserva por su ID
    public Optional<Reserva> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    // Eliminar una reserva por su ID
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}