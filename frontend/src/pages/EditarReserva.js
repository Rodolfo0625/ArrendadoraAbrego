import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const EditarReserva = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [reserva, setReserva] = useState({
    fecha_inicio: "",
    fecha_fin: "",
    id_cliente: "",
    id_vehiculo: "",
  });

  useEffect(() => {
    axios.get(`http://localhost:8080/api/reservas/${id}`).then((response) => {
      setReserva(response.data);
    });
  }, [id]);

  const handleUpdate = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/api/reservas/${id}`, reserva);
    navigate("/reservas");
  };

  return (
    <div className="container mx-auto mt-10">
      <h2 className="text-2xl mb-4">Editar Reserva</h2>
      <form onSubmit={handleUpdate}>
        <input
          type="text"
          placeholder="Cliente"
          value={reserva.id_cliente}
          onChange={(e) => setReserva({ ...reserva, id_cliente: e.target.value })}
          className="border p-2 w-full mb-2"
        />
        <input
          type="text"
          placeholder="Vehículo"
          value={reserva.id_vehiculo}
          onChange={(e) => setReserva({ ...reserva, id_vehiculo: e.target.value })}
          className="border p-2 w-full mb-2"
        />
        <button className="bg-green-500 text-white p-2 w-full">Guardar Cambios</button>
      </form>
    </div>
  );
};

export default EditarReserva;
