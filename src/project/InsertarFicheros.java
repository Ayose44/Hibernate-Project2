package AEDProject;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import proyecto.*;
import proyecto.SessionFactoryUtil;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class InsertarFicheros {
static String route;
static String route2;
	public static void main(String[] args) {
		JOptionPane jp = new JOptionPane();
		route = jp.showInputDialog("Tell me the route for the category file:");
		route2 = jp.showInputDialog("Tell me the route for the products file:");

		menu(route,route2);
	}

	public static void menu(String route, String route2){
		String[] opciones = { "Insert", "Modificar de la BD", "Borrar de la BD","Modificar de la BD","Limpiar Fichero","Salir" };
		List<String> optionList = new ArrayList<String>();
		optionList.add("Insert");
		optionList.add("Move to BD");
		optionList.add("Modify BD");
		optionList.add("Delete from BD");
		optionList.add("Reset file");
		optionList.add("Exit");
		
		
		Object[] options = optionList.toArray();
		int value = JOptionPane.showOptionDialog(null, "Choose an option", "Menu", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, optionList.get(0));
		String opt = optionList.get(value);
		switch (opt) {
		case "Insert":
			InsertarFich(route,route2);
			break;
		case "Modify BD":
			Modificar(route,route2);
			break;
		case "Delete from BD":
			Borrar(route,route2);
			break;
		case "Move to BD":
			Traspasar(route,route2);
			break;
		case "Reset file":
			LimpiarFichero(route,route2);
			break;
		case "Exit":
			System.exit(0);
			break;
		
		}
		
	}
	
	public static void InsertarFich(String route, String route2) {
		JOptionPane jp = new JOptionPane();
		int codc;
		String nombrec;
		String descripcionc;
		StringBuffer paso;
		int codp;
		String nombrep;
		int tamaño;
		String dosi;
		long tamano = 0;
		int codc2;
		Object[] codigos = new Object[200];
		try {
			File f = new File(route);
			if (f.exists()) {
				RandomAccessFile raf = new RandomAccessFile(f, "rw");
				for (int i = 0; i < 4; i++) {
					codc = Integer.parseInt(jp.showInputDialog("ID of category:"));
					raf.writeInt(codc);
					nombrec = jp.showInputDialog("Name of category:");
					paso = new StringBuffer(nombrec);
					paso.setLength(50);
					raf.writeChars(paso.toString());
					descripcionc = jp.showInputDialog("Description of category:");
					paso = new StringBuffer(descripcionc);
					paso.setLength(200);
					raf.writeChars(paso.toString());

				}
				tamano = raf.length();
				int numreg = 0;
				int i = 0;
				while (tamano > 504 * numreg) {
					raf.seek(504 * numreg);
					codigos[i] = raf.readInt();
					i++;
					numreg++;
					raf.skipBytes(500);
				}
			raf.close();

			} else {
				f.createNewFile();
				RandomAccessFile raf = new RandomAccessFile(f, "rw");
				for (int i = 0; i < 4; i++) {
					codc = Integer.parseInt(jp.showInputDialog("ID of category:"));
					raf.writeInt(codc);
					nombrec = jp.showInputDialog("Name of category:");
					paso = new StringBuffer(nombrec);
					paso.setLength(50);
					raf.writeChars(paso.toString());
					descripcionc = jp.showInputDialog("Description of category:");
					paso = new StringBuffer(descripcionc);
					paso.setLength(200);
					raf.writeChars(paso.toString());

				}
				tamano = raf.length();
				int numreg = 0;
				int i = 0;
				while (tamano > 504 * numreg) {
					raf.seek(504 * numreg);
					codigos[i] = raf.readInt();
					i++;
					numreg++;
					raf.skipBytes(500);
				}

				raf.close();
		
			}

			File f2 = new File(route2);
			if (f2.exists()) {
				RandomAccessFile raf2 = new RandomAccessFile(f2, "rw");
				StringBuffer paso2;
				for (int z = 0; z < 4; z++) {
					codp = Integer.parseInt(jp.showInputDialog("ID of product:"));
					raf2.writeInt(codp);
					nombrep = jp.showInputDialog("Name of product:");
					paso2 = new StringBuffer(nombrep);
					paso2.setLength(50);
					raf2.writeChars(paso2.toString());
					tamaño = Integer.parseInt(jp.showInputDialog("Size of product:"));
					raf2.writeInt(tamaño);
					dosi = jp.showInputDialog("Does the product can be replaced? ");
					paso2 = new StringBuffer(dosi);
					paso2.setLength(2);
					raf2.writeChars(paso2.toString());
					codc2 = (int) JOptionPane.showInputDialog(null, "Choose the ID of the category", "Category",
							JOptionPane.DEFAULT_OPTION, null, codigos, codigos[0]);
					raf2.writeInt(codc2);

				}
				raf2.close();
			} else {
				f2.createNewFile();
				RandomAccessFile raf2 = new RandomAccessFile(f2, "rw");
				StringBuffer paso2;
				for (int z = 0; z < 4; z++) {
					codp = Integer.parseInt(jp.showInputDialog("ID of product:"));
					raf2.writeInt(codp);
					nombrep = jp.showInputDialog("Name of product:");
					paso2 = new StringBuffer(nombrep);
					paso2.setLength(50);
					raf2.writeChars(paso2.toString());
					tamaño = Integer.parseInt(jp.showInputDialog("Size of product:"));
					raf2.writeInt(tamaño);
					dosi = jp.showInputDialog("Does the product can be replaced?");
					paso2 = new StringBuffer(dosi);
					paso2.setLength(2);
					raf2.writeChars(paso2.toString());
					codc2 = (int) JOptionPane.showInputDialog(null, "Choose the ID of the category", "Category",
							JOptionPane.DEFAULT_OPTION, null, codigos, codigos[0]);
					raf2.writeInt(codc2);

				}
				raf2.close();
				jp.showMessageDialog( null , "All inserted","Insert", JOptionPane.INFORMATION_MESSAGE);
				menu(route,route2);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			jp.showMessageDialog( null , "Error or Cancelled","Insert", JOptionPane.ERROR_MESSAGE);
			menu(route,route2);
			e.printStackTrace();
		}
	}
	
	public static void Traspasar(String route, String route2){
		try{
		JOptionPane jp = new JOptionPane();	
		File f = new File(route);
		File f2 = new File(route2);
		if (f.exists() && f2.exists()) {
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			RandomAccessFile raf2 = new RandomAccessFile(f2,"rw");
			long tamano2 = raf.length();
			int numreg2 = 0;
			int codigoca;
			String nombreca = "";
			String descripcionca = "";
			Configuration cfg = new Configuration().configure();
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			int pass=0;
			Categoria [] c = new Categoria[100];
			Producto [] p= new Producto[100];
			Categoria [] c2 = new Categoria[100];
			while (tamano2 > 504 * numreg2) {
				c[pass]=new Categoria();
				raf.seek(504 * numreg2);
				codigoca = raf.readInt();
				c[pass].setCodC((short) codigoca);
				for (int j = 0; j < 50; j++) {
					nombreca = nombreca + raf.readChar();
				}
				c[pass].setNombreC(nombreca);
				for (int j = 0; j < 200; j++) {
					descripcionca = descripcionca + raf.readChar();
				}
				c[pass].setDescripcion(descripcionca);
				session.save(c[pass]);
				pass++;
				nombreca="";
				descripcionca="";
				numreg2++;
				

			}
			
			long tamano3 = raf2.length();
			int numreg3 = 0;
			int codigopro;
			String nombrepro = "";
			int tampro;
			String dospro = "";
			int procodc;
			pass=0;
			
			while (tamano3 > 116 * numreg3) {
				p[pass]=new Producto();
				c2[pass] = new Categoria();
				raf2.seek(116 * numreg3);
				codigopro = raf2.readInt();
				p[pass].setCodP((short) codigopro);
				for (int z = 0; z < 50; z++) {
					nombrepro = nombrepro + raf2.readChar();
				}
				p[pass].setNombreP(nombrepro);
				tampro = raf2.readInt();
				p[pass].setTamano((short) tampro);
				for (int z = 0; z < 2; z++) {
					dospro = dospro + raf2.readChar();
				}
				p[pass].setDos(dospro);
				procodc = raf2.readInt();
				c2[pass] = (Categoria) session.load(Categoria.class,(short) procodc);
				p[pass].setCategoria(c2[pass]);
				session.save(p[pass]);
				nombrepro="";
				dospro="";
				pass++;
				numreg3++;
				
			}
			
			tx.commit();
			raf2.close();
			raf.close();
			session.close();
			JOptionPane.showMessageDialog(null, "All moved to the DataBase","Move to DB",JOptionPane.INFORMATION_MESSAGE);
			menu(route,route2);

		}else{
			jp.showMessageDialog( null , "Error,the files don't exist","Move to DB", JOptionPane.ERROR_MESSAGE);
			menu(route,route2);
		}
	} catch (IOException e){
		JOptionPane jp = new JOptionPane();
		jp.showMessageDialog( null , "Error", "Move to DB", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		menu(route,route2);

	}
	}
	
	public static void LimpiarFichero(String route, String route2){
		File f = new File(route);
		File f2 = new File(route2);
		int selectedOption = JOptionPane.showConfirmDialog(null, 
                "Are you sure of reset the file? Warning, the data can not be retrieved", 
                "Sure?", 
                JOptionPane.YES_NO_OPTION); 
		if (selectedOption == JOptionPane.YES_OPTION) {
			f.delete();
			f2.delete();
			JOptionPane.showMessageDialog(null,"Files reseted","Reset", JOptionPane.INFORMATION_MESSAGE);
			menu(route,route2);
		} else {
			menu(route,route2);

		}
		menu(route,route2);

	
	}
	
	public static void Modificar(String route, String route2){
		JOptionPane jp = new JOptionPane();
		String[] opciones = { "Categoria", "Producto","Volver atrás" };
		List<String> optionList = new ArrayList<String>();
		optionList.add("Category");
		optionList.add("Product");
		optionList.add("Back");

		Object[] options = optionList.toArray();
		int value = JOptionPane.showOptionDialog(null, "What table do you want to modify?", "Modify", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, optionList.get(0));
		String opt = optionList.get(value);
		switch (opt) {
		case "Category":
			try{
			Configuration cfg =new Configuration().configure();
			SessionFactory sessionFactory=cfg.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			Categoria c = new Categoria();
			Query q=session.createQuery("from Categoria");
			List <Categoria> lista=q.list();
			Iterator <Categoria>iter=lista.iterator();
			int [] codc = new int [10];
			String [] desc = new String[10];
			String[] nomc = new String[10];
			int i=0;
			while(iter.hasNext())
			{
			c=(Categoria)iter.next();
			codc[i] = c.getCodC();
			desc[i] = c.getDescripcion();
			nomc[i] = c.getNombreC();
			i++;
			}
			
			String [] mostrar = new String [codc.length];
			for(int z=0;z<codc.length;z++){
				mostrar[z] = "ID:| " + String.valueOf(codc[z]) + "| Name: " + nomc[z] + "| Description: " + desc[z];
			}
			String codc2 = (String) JOptionPane.showInputDialog(null, "Choose a category", "Category",
					JOptionPane.DEFAULT_OPTION, null, mostrar, mostrar[0]);
			
			String codca = codc2.substring(9,codc2.length()).trim();
			String [] sep = codca.split("|");
			String vcodca = sep[0];
			
			int codca2 = Integer.parseInt(vcodca);
			c=(Categoria)session.load(Categoria.class,(short) codca2);
			String[] opciones2 = { "Nombre", "Descripcion"};
			List<String> optionList2 = new ArrayList<String>();
			optionList2.add("Name");
			optionList2.add("Description");
			Object[] options2 = optionList2.toArray();
			int value2 = JOptionPane.showOptionDialog(null, "Which fields do you want to modify?", "Modify", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options2, optionList2.get(0));
			String opt3 = optionList2.get(value2);
			try{
			if(opt3=="Name"){
			String nuevonom=JOptionPane.showInputDialog("New name:");
			c.setNombreC(nuevonom);	
			session.save(c);
			tx.commit();
			session.close();
			}else{
				if(opt3=="Description"){
					String nuevadesc = JOptionPane.showInputDialog("New description:");
					c.setDescripcion(nuevadesc);
					session.save(c);
					tx.commit();
					session.close();
				}
			}
			JOptionPane.showMessageDialog(null,"Modified","Modify",JOptionPane.INFORMATION_MESSAGE);
			menu(route,route2);
			}catch(Exception e){
				JOptionPane.showMessageDialog( null , "Error","Modify", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				menu(route,route2);
			}
			}catch(Exception e){
				e.printStackTrace();
				menu(route,route2);
			}
			break;
		case "Product":
			try{
				Configuration cfg =new Configuration().configure();
				SessionFactory sessionFactory=cfg.buildSessionFactory();
				Session session=sessionFactory.openSession();
				Transaction tx=session.beginTransaction();
				Producto p = new Producto();
				Query q=session.createQuery("from Producto");
				List <Producto> lista=q.list();
				Iterator <Producto>iter=lista.iterator();
				int [] codp = new int [10];
				String [] dosp = new String[10];
				String[] nomp = new String[10];
				int [] tam = new int[10];
				int i=0;
				Categoria [] c = new Categoria[10];
				while(iter.hasNext())
				{
				p=(Producto)iter.next();
				c[i] = p.getCategoria();
				dosp[i] = p.getDos();
				nomp[i] = p.getNombreP();
				tam[i]=p.getTamano();
				codp[i] = p.getCodP();
				i++;
				}
				String [] mostrar = new String [codp.length];
				for(int z=0;z<codp.length;z++){
					mostrar[z] = "ID:| " + String.valueOf(codp[z]) + "| Name: " + nomp[z] + "| Replaced: " + dosp[z] + "| Size: " + tam[z]  ;
				}
				String codc2 = (String) JOptionPane.showInputDialog(null, "Choose a product", "Product",
						JOptionPane.DEFAULT_OPTION, null, mostrar, mostrar[0]);
				
				String codca = codc2.substring(9,codc2.length()).trim();
				String [] sep = codca.split("|");
				String vcodca = sep[0];
			
				int codca2 = Integer.parseInt(vcodca);
				p=(Producto)session.load(Producto.class,(short) codca2);
				String[] opciones2 = { "Nombre", "Dosifica" , "Tamaño" , "Categoria"};
				List<String> optionList2 = new ArrayList<String>();
				optionList2.add("Name");
				optionList2.add("Replaced");
				optionList2.add("Size");
				optionList2.add("Category");
				Object[] options2 = optionList2.toArray();
				int value2 = JOptionPane.showOptionDialog(null, "Which fields do you want to modify?", "Modify", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options2, optionList2.get(0));
				String opt3 = optionList2.get(value2);
		
				try{
				if(opt3=="Name"){
				String nuevonom=JOptionPane.showInputDialog("New name");
				p.setNombreP(nuevonom);;	
				session.save(p);
				tx.commit();
				session.close();
				}else{
					if(opt3=="Replaced"){
						String nuevadesc = JOptionPane.showInputDialog("Can be replaced?");
						p.setDos(nuevadesc);;
						session.save(p);
						tx.commit();
						session.close();
					} else{
						if(opt3=="Size"){
							String tam2 = JOptionPane.showInputDialog("New size");
							int vtam = Integer.parseInt(tam2);
							p.setTamano((short) vtam);
							session.save(p);
							tx.commit();
							session.close();
						}else{
							if(opt3=="Category");
							Categoria c3 = new Categoria();
							int cat = (int) JOptionPane.showInputDialog(null, "ID of Category:", "Modify category",
									JOptionPane.DEFAULT_OPTION, null, c, c[0]);
							c3 = (Categoria) session.load(Categoria.class,(short) cat);
							p.setCategoria(c3);
							session.save(p);
							tx.commit();
							session.close();
							
						}
					}
				}
				JOptionPane.showMessageDialog(null,"Modified","Modify",JOptionPane.INFORMATION_MESSAGE);
				menu(route,route2);
				}catch(Exception e){
					JOptionPane.showMessageDialog( null , "Error","Modify", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					menu(route,route2);
				}
				}catch(Exception e){
					e.printStackTrace();
					menu(route,route2);
				}
			break;
		case "Back":
			menu(route,route2);
			break;
		
		}
	}
	
	public static void Borrar(String route, String route2){
		JOptionPane jp = new JOptionPane();
		String[] opciones = { "Categoria", "Producto","Volver atrás" };
		List<String> optionList = new ArrayList<String>();
		optionList.add("Category");
		optionList.add("Product");
		optionList.add("Back");

		Object[] options = optionList.toArray();
		int value = JOptionPane.showOptionDialog(null, "From which table do you want to delete?", "Delete", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, optionList.get(0));
		String opt = optionList.get(value);
		switch (opt) {
		case "Category":
			try{
			Configuration cfg =new Configuration().configure();
			SessionFactory sessionFactory=cfg.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			Categoria c = new Categoria();
			Query q=session.createQuery("from Categoria");
			List <Categoria> lista=q.list();
			Iterator <Categoria>iter=lista.iterator();
			int [] codc = new int [10];
			String [] desc = new String[10];
			String[] nomc = new String[10];
			int i=0;
			while(iter.hasNext())
			{
			c=(Categoria)iter.next();
			codc[i] = c.getCodC();
			desc[i] = c.getDescripcion();
			nomc[i] = c.getNombreC();
			i++;
			}
			
			String [] mostrar = new String [codc.length];
			for(int z=0;z<codc.length;z++){
				mostrar[z] = "ID:| " + String.valueOf(codc[z]) + "| Name: " + nomc[z] + "| Description: " + desc[z];
			}
			String codc2 = (String) JOptionPane.showInputDialog(null, "Seleccione una categoria", "Categoria",
					JOptionPane.DEFAULT_OPTION, null, mostrar, mostrar[0]);
			
			String codca = codc2.substring(9,codc2.length()).trim();
			String [] sep = codca.split("|");
			String vcodca = sep[0];
			int codcasi = Integer.parseInt(vcodca);
			Categoria c2 = new Categoria();
			c2=(Categoria)session.load(Categoria.class,(short) codcasi);
			session.delete(c2);
			tx.commit();
			session.close();
			JOptionPane.showMessageDialog(null,"Deleted","Delete", JOptionPane.INFORMATION_MESSAGE);
				menu(route,route2);
			
			
			
			}catch(Exception e){
				JOptionPane.showMessageDialog( null , "Error","Delete", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				menu(route,route2);
			}
			
			break;
		case "Product":
			try{
				Configuration cfg =new Configuration().configure();
				SessionFactory sessionFactory=cfg.buildSessionFactory();
				Session session=sessionFactory.openSession();
				Transaction tx=session.beginTransaction();
				Producto p = new Producto();
				Query q=session.createQuery("from Producto");
				List <Producto> lista=q.list();
				Iterator <Producto>iter=lista.iterator();
				int [] codp = new int [10];
				String [] dosp = new String[10];
				String[] nomp = new String[10];
				int [] tam = new int[10];
				int i=0;
				Categoria [] c = new Categoria[10];
				while(iter.hasNext())
				{
				p=(Producto)iter.next();
				c[i] = p.getCategoria();
				dosp[i] = p.getDos();
				nomp[i] = p.getNombreP();
				tam[i]=p.getTamano();
				codp[i] = p.getCodP();
				i++;
				}
				String [] mostrar = new String [codp.length];
				for(int z=0;z<codp.length;z++){
					mostrar[z] = "ID:| " + String.valueOf(codp[z]) + "| Name: " + nomp[z] + "| Replaced: " + dosp[z] + "| Size: " + tam[z]  ;
				}
				String codc2 = (String) JOptionPane.showInputDialog(null, "Choose a product", "Product",
						JOptionPane.DEFAULT_OPTION, null, mostrar, mostrar[0]);
				
				String codca = codc2.substring(9,codc2.length()).trim();
				String [] sep = codca.split("|");
				String vcodca = sep[0];
				int codca2 = Integer.parseInt(vcodca);
				p=(Producto)session.load(Producto.class,(short) codca2);
			
					session.delete(p);
					tx.commit();
					session.close();
					JOptionPane.showMessageDialog(null,"Deleted","Delete", JOptionPane.INFORMATION_MESSAGE);
					menu(route,route2);
			
				
				}catch(Exception e){
					e.printStackTrace();
					menu(route,route2);
				}
			break;
		case "Back":
			menu(route,route2);
			break;
		
		}
	}
}
