package orquestradordemo2;

import numeroPlaca.EventosnumeroPlaca;
import numeroSerie.EventosnumeroSerie;
import java.io.File;
import java.util.Scanner;

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
            JFileChooser jfilechooser = new JFileChooser("C:\\Users\\IBM_ADMIN\\Desktop\\orquestradorDemo2\\orquestradorDemo2\\src\\data");
            FileNameExtensionFilter filenameextensionfilter = new FileNameExtensionFilter("Archivos XML", "xml");
            jfilechooser.setFileFilter(filenameextensionfilter);
            int seleccionado = jfilechooser.showOpenDialog(null);
            String archivoSeleccionado = jfilechooser.getSelectedFile().getAbsolutePath();
            
            JFrame jframe = new JFrame();
            jframe.setTitle(archivoSeleccionado);
            jframe.setSize(500, 500);
            JPanel jpanel = new JPanel();
            JLabel jlabel1 = new JLabel();
            JLabel jlabel2 = new JLabel();
            JLabel jlabel3 = new JLabel();
            JLabel jlabel4 = new JLabel();
            
            jpanel.add(jlabel1);
            jpanel.add(jlabel2);
            jpanel.add(jlabel3);
            jpanel.add(jlabel4);
            jframe.add(jpanel);

            File file = new File(archivoSeleccionado);

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
                System.out.println("Tipo de Vehiculo: " + siniestros.getTipoVehiculo());
                System.out.println("Uso del Vehiculo: " + siniestros.getUsoVehiculo() + " - " + claveUsoVehiculo[siniestros.getUsoVehiculo()] + "\n");
        	}
            
            for(NumeroPlaca numeroplaca : siniestros.getNumeroPlaca())
            {
                for(EventosnumeroPlaca eventosnumeroplaca : numeroplaca.getEventos_numeroPlaca())
                {
                	if(eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() > 4 && eventosnumeroplaca.getEventosReglasID_numeroPlaca().equals("H01") 
                			|| eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() > 4 && eventosnumeroplaca.getEventosReglasID_numeroPlaca().equals("H01b")
                			|| eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() > 5 && eventosnumeroplaca.getEventosReglasID_numeroPlaca().equals("H01c"))
                	{
                		jlabel1.setText("La Placa: '" + numeroplaca.getId_numeroPlaca() + "' tiene " + eventosnumeroplaca.getEventosReglasTotales_numeroPlaca() + 
                				" eventos con la regla '" + eventosnumeroplaca.getEventosReglasID_numeroPlaca() + "'.");
                	}
                }
                System.out.println("No hay más problemas con el número de Placa: " + numeroplaca.getId_numeroPlaca() + "\n");
            }

            for(NumeroSerie numeroserie : siniestros.getNumeroSerie())
            {                
                for(EventosnumeroSerie eventosnumeroserie : numeroserie.getEventos_numeroSerie())
                {
                	if(eventosnumeroserie.getEventosReglasTotales_numeroSerie() > 4 && eventosnumeroserie.getEventosReglasID_numeroSerie().equals("H01") 
                			|| eventosnumeroserie.getEventosReglasTotales_numeroSerie() > 4 && eventosnumeroserie.getEventosReglasID_numeroSerie().equals("H01b")
                			|| eventosnumeroserie.getEventosReglasTotales_numeroSerie() > 5 && eventosnumeroserie.getEventosReglasID_numeroSerie().equals("H01c"))
                	{
                		jlabel2.setText("El número de Serie: '" + numeroserie.getId_numeroSerie() + "' tiene " + eventosnumeroserie.getEventosReglasTotales_numeroSerie() + 
                				" eventos con la regla '" + eventosnumeroserie.getEventosReglasID_numeroSerie() + "'.");
                	}
                }
                System.out.println("No hay más problemas con el número de Serie: " + numeroserie.getId_numeroSerie() + "\n");
            }
            
            for(RFCContratante rfccontratante : siniestros.getRfcContratante())
            {
                for(EventosrfcContratante eventosrfccontratante : rfccontratante.getEventosrfccontratente())
                {
                    if(eventosrfccontratante.getEventosReglasTotales_rfcContratante() > 4 && eventosrfccontratante.getEventosReglasID_rfcContratante().equals("H01") 
                    		|| eventosrfccontratante.getEventosReglasTotales_rfcContratante() > 4 && eventosrfccontratante.getEventosReglasID_rfcContratante().equals("H01b")
                    		|| eventosrfccontratante.getEventosReglasTotales_rfcContratante() > 5 && eventosrfccontratante.getEventosReglasID_rfcContratante().equals("H01c"))
                    {
                        jlabel3.setText("El RFC del Contratante '" + rfccontratante.getId_rfcContratante() + "' tiene " + eventosrfccontratante.getEventosReglasTotales_rfcContratante() + 
                        		" eventos con la regla '" + eventosrfccontratante.getEventosReglasID_rfcContratante() + "'.");
                    }
                }
            }

            for(RFCConductor rfcconductor : siniestros.getRfcConductor())
            {
                for(EventosrfcConductor eventosrfcconductor : rfcconductor.getEventosrfcconductor())
                {
                    if(eventosrfcconductor.getEventosReglasTotales_rfcConductor() > 4 && eventosrfcconductor.getEventosReglasID_rfcConductor().equals("H01")
                    		|| eventosrfcconductor.getEventosReglasTotales_rfcConductor() > 4 && eventosrfcconductor.getEventosReglasID_rfcConductor().equals("H01b")
                    		|| eventosrfcconductor.getEventosReglasTotales_rfcConductor() > 5 && eventosrfcconductor.getEventosReglasID_rfcConductor().equals("H01c"))
                    {
                        jlabel4.setText("El RFC del Conductor '" + rfcconductor.getId_rfcConductor() + "' tiene " + eventosrfcconductor.getEventosReglasTotales_rfcConductor() + 
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
