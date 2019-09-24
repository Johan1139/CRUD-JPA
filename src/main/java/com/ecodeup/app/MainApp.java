package com.ecodeup.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.ecodeup.model.Empleados;

public class MainApp {

	public static void main(String[] args) {
		
		int opcion = 0;
		String menu="";
		Empleados empleado;
		
		//entity trae el factory(Objeto con toda la conexión de la BD)
		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		//Menú
		menu+="Elija una opción: \n";
		menu+="1.Registrar Empleado \n";
		menu+="2.Buscar Empleado \n";
		menu+="3.Actualizar Empleado \n";
		menu+="4.Eliminar Empleado \n";
		menu+="5.Salir \n";
		
		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
			
			switch(opcion){
			case 1:
				try{
					empleado = new Empleados();
	
					empleado.setDocumento(JOptionPane.showInputDialog("Digite el documento del empleado:").trim());
					empleado.setNombres(JOptionPane.showInputDialog("Digite los nombres del empleado:").trim());
					empleado.setApellidos(JOptionPane.showInputDialog("Digite los apellidos del empleado:").trim());
					empleado.setCargo(JOptionPane.showInputDialog("Digite el cargo del empleado:").trim());
					empleado.setSalario(Integer.parseInt(JOptionPane.showInputDialog("Digite el salario del empleado:").trim()));
					
					System.out.println(empleado);

					//Para poder Guardar
					entity.getTransaction().begin();
					//Guarda el Obejto en la BD (INSERT DE LA BD)
					entity.persist(empleado);
					//El commit de la BD
					entity.getTransaction().commit();
					JOptionPane.showMessageDialog(null, "Registro Exitoso");

				}catch (Exception e){
					JOptionPane.showMessageDialog(null, "No se pudo registrar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case 2:
				empleado = new Empleados();
				empleado.setDocumento(JOptionPane.showInputDialog("Digite el documento del empleado a buscar:"));
				//Se le pasa dos parametros el primero es la clase donde esta instanciado el objeto de la BD
				//en el segundo pasa el id
				empleado = entity.find(Empleados.class, empleado.getDocumento());
				
				if(empleado != null){
					System.out.println(empleado);
					
					String mensaje = "Documento: " + empleado.getDocumento() + "\n"
							+ "Nombres: " + empleado.getNombres() + "\n"
							+ "Apellidos: " + empleado.getApellidos() + "\n"
							+ "Cargo: " + empleado.getCargo() + "\n"
							+ "Salario: " + empleado.getSalario();
					
					JOptionPane.showMessageDialog(null, mensaje);
					
					System.out.println();
				} else {
					JOptionPane.showMessageDialog(null, "Empleado NO encontrado", "Error", JOptionPane.ERROR_MESSAGE);
					
					//lista de Productos
					List<Empleados> listaEmpleados = new ArrayList<>();
					
					//Reoje todos los registros de la BD productos
					//En el Select se hace referencia al la CLASE Producto
					Query query= entity.createQuery("SELECT e FROM Empleados e");
					listaEmpleados = query.getResultList();
					
					for(Empleados p : listaEmpleados){
						System.out.println(p);
					}
					System.out.println();
				}
				break;
				
			case 3:
				empleado = new Empleados();
				empleado.setDocumento(JOptionPane.showInputDialog("Digite el documento del empleado a actualizar:"));
				empleado = entity.find(Empleados.class, empleado.getDocumento());
				
				if(empleado != null){
					System.out.println(empleado);
					String mensaje = "Documento: " + empleado.getDocumento() + "\n"
							+ "Nombres: " + empleado.getNombres() + "\n"
							+ "Apellidos: " + empleado.getApellidos() + "\n"
							+ "Cargo: " + empleado.getCargo() + "\n"
							+ "Salario: " + empleado.getSalario();
					
					JOptionPane.showMessageDialog(null, mensaje);
					
					empleado.setNombres(JOptionPane.showInputDialog("Digite los nombres del empleado:"));
					empleado.setApellidos(JOptionPane.showInputDialog("Digite los apellidos del empleado:"));
					empleado.setCargo(JOptionPane.showInputDialog("Digite el cargo del empleado:"));
					empleado.setSalario(Integer.parseInt(JOptionPane.showInputDialog("Digite salario del empleado:")));
					
					entity.getTransaction().begin();
					//Para actuaizar se usa el metodo merge
					entity.merge(empleado);
					entity.getTransaction().commit();
					
					JOptionPane.showMessageDialog(null, "Empleado actualizado");
					
				} else {
					JOptionPane.showMessageDialog(null, "Empleado NO encontrado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case 4:
				try {
					empleado = new Empleados();
					empleado.setDocumento(JOptionPane.showInputDialog("Digite el documento del empleado a eliminar:"));
				
					if (empleado != null){
						empleado = entity.find(Empleados.class, empleado.getDocumento());
						System.out.println(empleado);
						
						entity.getTransaction().begin();
						//remove Elimina el producto de la BD
						entity.remove(empleado);
						entity.getTransaction().commit();
						
						JOptionPane.showMessageDialog(null, "Empleado eliminado");			
					} 
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case 5: entity.close();JPAUtil.shutdown();
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "Opción no válida", "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}while (opcion != 5);
	}
}
