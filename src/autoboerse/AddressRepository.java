package autoboerse;

import spize.persistence.Persistence;

public class AddressRepository extends Repository<Address> implements IRepository<Address> {

	public AddressRepository() {
		super(Address.class);
	}

	public Address createAddress(int personId, String street, int postalCode, String location) {
		Address address = new Address(personId, street, postalCode, location);
		entityManager.persist(address);
		return address;
	}

	static void reset() {
		Persistence.resetTable(schema, table);
	}

	static final String schema = "public";
	static final String table = "ADDRESS";
}