import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Reservas from "./pages/Reservas";
import EditarReserva from "./pages/EditarReserva";
import Navbar from "./components/navbar";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/reservas" element={<Reservas />} />
        <Route path="/reservas/editar/:id" element={<EditarReserva />} />
      </Routes>
    </Router>
  );
}

export default App;

