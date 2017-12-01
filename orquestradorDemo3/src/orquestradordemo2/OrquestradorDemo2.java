package orquestradordemo2;

import java.awt.Font;
import numeroPlaca.EventosnumeroPlaca;
import numeroSerie.EventosnumeroSerie;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.swing.*;
import siniestros.Siniestros;
import numeroPlaca.NumeroPlaca;
import numeroSerie.NumeroSerie;
import rfcConductor.EventosrfcConductor;
import rfcConductor.RFCConductor;
import rfcContratante.EventosrfcContratante;
import rfcContratante.RFCContratante;

public class OrquestradorDemo2 
{
    public static void main(String[] args) 
    {
        try
        {            
            JFileChooser jfilechooser = new JFileChooser();
            FileNameExtensionFilter filenameextensionfilter = new FileNameExtensionFilter("Archivos XML", "xml");
            jfilechooser.setFileFilter(filenameextensionfilter);
            int seleccionado = jfilechooser.showOpenDialog(null);
            String archivoSeleccionado = jfilechooser.getSelectedFile().getAbsolutePath();
            
            File file = new File(archivoSeleccionado);
            
            JFrame jframe = new JFrame();
            jframe.setTitle(archivoSeleccionado);
            jframe.setSize(1200, 500);
            JPanel jpanel = new JPanel();
            
            JLabel jlabel1 = new JLabel();
            JLabel jlabel2 = new JLabel();
            JLabel jlabel3 = new JLabel();
            JLabel jlabel4 = new JLabel();
            JLabel jlabel5 = new JLabel();
            JLabel jlabel6 = new JLabel();
            JLabel jlabel7 = new JLabel();
            
            jlabel1.setFont(new Font("Arial", Font.PLAIN, 40));
            jlabel2.setFont(new Font("Arial", Font.PLAIN, 40));
            jlabel3.setFont(new Font("Arial", Font.PLAIN, 40));
            jlabel4.setFont(new Font("Arial", Font.PLAIN, 40));
            jlabel5.setFont(new Font("Arial", Font.PLAIN, 40));
            jlabel6.setFont(new Font("Arial", Font.PLAIN, 40));
            jlabel7.setFont(new Font("Arial", Font.PLAIN, 40));
            
            jpanel.add(jlabel1);
            jpanel.add(jlabel2);
            jpanel.add(jlabel3);
            jpanel.add(jlabel4);
            jpanel.add(jlabel5);
            jpanel.add(jlabel6);
            jpanel.add(jlabel7);
            
            jframe.add(jpanel);
            
            Boolean miSwitch = false;

            if(seleccionado == JFileChooser.APPROVE_OPTION)
            {
                System.out.println("El archivo seleccionado es: " + jfilechooser.getSelectedFile().getName() + "\n");
            }

            JAXBContext jaxbcontext = JAXBContext.newInstance(Siniestros.class);
            Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
            Siniestros siniestros = (Siniestros) unmarshaller.unmarshal(file);
            
            String[] claveUsoVehiculo = {"", "Particular", "Privado", "", "Transporte Público", "Renta Diaria", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Chofer Privado"};
            
        	if(siniestros.getTipoVehiculo().equals("AUT")  || siniestros.getTipoVehiculo().equals("CA1") 
                    && siniestros.getUsoVehiculo() == 01 || siniestros.getUsoVehiculo() == 02 || siniestros.getUsoVehiculo() == 04 || siniestros.getUsoVehiculo() == 05 || siniestros.getUsoVehiculo() == 23)
        	{
                    System.out.println("Entra a H01! \n");
                    jlabel1.setText("Tipo de Vehiculo: '" + siniestros.getTipoVehiculo() + "' y ");
                    jlabel2.setText("Uso del Vehiculo: '" + siniestros.getUsoVehiculo() + "' - " + claveUsoVehiculo[siniestros.getUsoVehiculo()] + "\n");
        	}
        	else
        	{
                    jlabel1.setText("Tipo de Siniestro: '" + siniestros.getTipoSiniestro() + "'.");
                    miSwitch = true;
        	}
            
            for(NumeroPlaca numeroplaca : siniestros.getNumeroPlaca())
            {
                for(EventosnumeroPlaca eventosnumeroplaca : numeroplaca.getEventos_numeroPlaca())
                {
                    if(!miSwitch && eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() > 4 && eventosnumeroplaca.getEventosReglasID_numeroPlaca().equals("H01") 
                        || !miSwitch && eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() > 4 && eventosnumeroplaca.getEventosReglasID_numeroPlaca().equals("H01b")
                        || !miSwitch && eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() > 5 && eventosnumeroplaca.getEventosReglasID_numeroPlaca().equals("H01c"))
                    {
                        jlabel3.setText("La Placa: '" + numeroplaca.getId_numeroPlaca() + "' tiene " + eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() + 
                            " eventos con la regla '" + eventosnumeroplaca.getEventosReglasID_numeroPlaca() + "'.");
                    }
                    else if(miSwitch && eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() > 4 && eventosnumeroplaca.getEventosReglasID_numeroPlaca().equals("H07"))
                    {
                        jlabel3.setText("La Placa: '" + numeroplaca.getId_numeroPlaca() + "' tiene " + eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() + 
                            " eventos con la regla '" + eventosnumeroplaca.getEventosReglasID_numeroPlaca() + "'.");
                    }
                }
                // System.out.println("No hay más problemas con el número de Placa: " + numeroplaca.getId_numeroPlaca() + "\n");
            }

            for(NumeroSerie numeroserie : siniestros.getNumeroSerie())
            {                
                for(EventosnumeroSerie eventosnumeroserie : numeroserie.getEventos_numeroSerie())
                {
                    if(!miSwitch && eventosnumeroserie.getEventosReglasTotales_numeroSerie() > 4 && eventosnumeroserie.getEventosReglasID_numeroSerie().equals("H01") 
                        || !miSwitch && eventosnumeroserie.getEventosReglasTotales_numeroSerie() > 4 && eventosnumeroserie.getEventosReglasID_numeroSerie().equals("H01b")
                        || !miSwitch && eventosnumeroserie.getEventosReglasTotales_numeroSerie() > 5 && eventosnumeroserie.getEventosReglasID_numeroSerie().equals("H01c"))
                    {
                        jlabel4.setText("El número de Serie: '" + numeroserie.getId_numeroSerie() + "' tiene " + eventosnumeroserie.getEventosReglasTotales_numeroSerie() + 
                            " eventos con la regla '" + eventosnumeroserie.getEventosReglasID_numeroSerie() + "'.");
                    }
                    else if(miSwitch && eventosnumeroserie.getEventosReglasTotales_numeroSerie() > 4 && eventosnumeroserie.getEventosReglasID_numeroSerie().equals("H07"))
                    {
                        jlabel4.setText("El número de Serie:'" + numeroserie.getId_numeroSerie() + "' tiene " + eventosnumeroserie.getEventosReglasTotales_numeroSerie() + 
                            " eventos con la regla '" + eventosnumeroserie.getEventosReglasID_numeroSerie() + "'.");
                    }
                }
                // System.out.println("No hay más problemas con el número de Serie: " + numeroserie.getId_numeroSerie() + "\n");
            }
          
            for(RFCContratante rfccontratante : siniestros.getRfcContratante())
            {
                for(EventosrfcContratante eventosrfccontratante : rfccontratante.getEventosrfccontratente())
                {
                    if(!miSwitch && eventosrfccontratante.getEventosReglasTotales_rfcContratante() > 4 && eventosrfccontratante.getEventosReglasID_rfcContratante().equals("H01") 
                    		|| !miSwitch && eventosrfccontratante.getEventosReglasTotales_rfcContratante() > 4 && eventosrfccontratante.getEventosReglasID_rfcContratante().equals("H01b")
                    		|| !miSwitch && eventosrfccontratante.getEventosReglasTotales_rfcContratante() > 5 && eventosrfccontratante.getEventosReglasID_rfcContratante().equals("H01c"))
                    {
                        jlabel5.setText("El RFC del Contratante '" + rfccontratante.getId_rfcContratante() + "' tiene " + eventosrfccontratante.getEventosReglasTotales_rfcContratante() + 
                        		" eventos con la regla '" + eventosrfccontratante.getEventosReglasID_rfcContratante() + "'.");
                    }
                }
            }

            for(RFCConductor rfcconductor : siniestros.getRfcConductor())
            {
                for(EventosrfcConductor eventosrfcconductor : rfcconductor.getEventosrfcconductor())
                {
                    if(!miSwitch && eventosrfcconductor.getEventosReglasTotales_rfcConductor() > 4 && eventosrfcconductor.getEventosReglasID_rfcConductor().equals("H01")
                    		|| !miSwitch && eventosrfcconductor.getEventosReglasTotales_rfcConductor() > 4 && eventosrfcconductor.getEventosReglasID_rfcConductor().equals("H01b")
                    		|| !miSwitch && eventosrfcconductor.getEventosReglasTotales_rfcConductor() > 5 && eventosrfcconductor.getEventosReglasID_rfcConductor().equals("H01c"))
                    {
                        jlabel6.setText("El RFC del Conductor '" + rfcconductor.getId_rfcConductor() + "' tiene " + eventosrfcconductor.getEventosReglasTotales_rfcConductor() + 
                        		" eventos con la regla '" + eventosrfcconductor.getEventosReglasID_rfcConductor() + "'.");
                    }
                }
            }
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setVisible(true);
        }
        catch(JAXBException e) { e.printStackTrace(); }
    }   
}