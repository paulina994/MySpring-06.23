package entities.inheritance;

import entities.inheritance.TransportationVehicle;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "trucks")
public class Truck extends TransportationVehicle {

        private static final String TYPE = "TRUCK";

        public Truck() {
        }

        public Truck(int numOfContainers) {
            super(numOfContainers);
        }
    }

