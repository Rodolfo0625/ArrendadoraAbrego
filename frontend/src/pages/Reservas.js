import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const Reservas = () => {
  const [reservas, setReservas] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/reservas").then((response) => {
      setReservas(response.data);
    });
  }, []);

  return (
    <div className="container mx-auto mt-10">
      <h2 className="text-2xl mb-4">Reservas</h2>
      <table className="w-full border-collapse border">
        <thead>
          <tr className="bg-gray-200">
            <th className="border p-2">Cliente</th>
            <th className="border p-2">Vehículo</th>
            <th className="border p-2">Fecha Inicio</th>
            <th className="border p-2">Fecha Fin</th>
            <th className="border p-2">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {reservas.map((reserva) => (
            <tr key={reserva.id}>
              <td className="border p-2">{reserva.cliente_id}</td>
              <td className="border p-2">{reserva.id_vehiculo}</td>
              <td className="border p-2">{reserva.fecha_inicio}</td>
              <td className="border p-2">{reserva.fecha_fin}</td>
              <td className="border p-2">
                <Link to={`/reservas/editar/${reserva.id}`} className="text-blue-500">Editar</Link> | 
                <button className="text-red-500 ml-2" onClick={() => borrarReserva(reserva.id)}>Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const borrarReserva = async (id) => {
  if (window.confirm("¿Seguro que quieres eliminar esta reserva?")) {
    await axios.delete(`http://localhost:8080/api/reservas/${id}`);
    window.location.reload();
  }
};

export default Reservas;
