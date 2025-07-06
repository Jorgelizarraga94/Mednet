package med.net.api.domain.Consulta;

import med.net.api.domain.Consulta.Validaciones.ValidadorDeConsultas;
import med.net.api.domain.Medico.Medico;
import med.net.api.domain.Medico.MedicoRepository;
import med.net.api.domain.Paciente.PacienteRepository;
import med.net.api.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta reservar ( DatosReservaConsulta datos){
        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el ID informado");
        }
        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un medico con el ID informado");
        }

        //validaciones

        validadores.forEach(v -> v.validar(datos));

        var medico = elegirMedico(datos);
        if(medico == null){
            throw new ValidacionException("No existe un medico disponible en ese horario");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente,datos.fecha(), null);
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elije un medico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad().name(), datos.fecha());
    }
    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("Id de la consulta informado no existe!");
        }
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
}
