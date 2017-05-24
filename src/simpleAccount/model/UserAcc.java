package simpleAccount.model;

public class UserAcc {
	
	private String name;
	private Integer id;
	private Double amount;
	
	public UserAcc(String name, Integer id, Double amount) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.id = id;
		this.amount = amount;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	/**
	 * 
	 */
	public String getListItemB() {
		return (this.id.toString() + " - " + this.name);
	}
}
