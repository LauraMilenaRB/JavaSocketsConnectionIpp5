/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.Filtros.BlueprintFiltroException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class BlueprintFiltroSubmuestreoTest {
    
    @Test
    public void filtrarBlueprints() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Set<Blueprint> setBlueprints=new HashSet<>();
        Blueprint bp=new Blueprint("lala", "thepaint",pts);
        Blueprint bp1=new Blueprint("lala", "thepaint1",pts);
        Blueprint bp2=new Blueprint("lala", "thepaint2",pts);
        Blueprint bp3=new Blueprint("john", "thepaint0",pts);
        setBlueprints.add(bp);
        setBlueprints.add(bp1);
        setBlueprints.add(bp2);
        try {
            ibpp.saveBlueprint(bp);
            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);
            ibpp.saveBlueprint(bp3);
        } catch (BlueprintFiltroException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        try {
            Set<Blueprint> s= ibpp.getBlueprintByAuthor("lala");
            assertEquals("El conjunto de Blueprints no corresponde al conjunto del author",s.contains(bp3),false);
            assertEquals("El conjunto de Blueprints no contiene todos los Blueprints del autor",s,setBlueprints);
        } catch (BlueprintNotFoundException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
                
        
    }
    
    @Test
    public void filtrarBlueprint() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Set<Blueprint> setBlueprints=new HashSet<>();
        Blueprint bp=new Blueprint("lala", "thepaint",pts);
        Blueprint bp1=new Blueprint("lala", "thepaint1",pts);
        Blueprint bp2=new Blueprint("lala", "thepaint2",pts);
        Blueprint bp3=new Blueprint("john", "thepaint0",pts);
        setBlueprints.add(bp);
        setBlueprints.add(bp1);
        setBlueprints.add(bp2);
        try {
            ibpp.saveBlueprint(bp);
            ibpp.saveBlueprint(bp1);
            ibpp.saveBlueprint(bp2);
            ibpp.saveBlueprint(bp3);
        } catch (BlueprintFiltroException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        try {
            Set<Blueprint> s= ibpp.getBlueprintByAuthor("lala");
            assertEquals("El conjunto de Blueprints no corresponde al conjunto del author",s.contains(bp3),false);
            assertEquals("El conjunto de Blueprints no contiene todos los Blueprints del autor",s,setBlueprints);
        } catch (BlueprintNotFoundException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
                
        
    }
}

