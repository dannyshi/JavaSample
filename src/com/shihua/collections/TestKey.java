package com.shihua.collections;

public class TestKey {
	
	public String name;
	
	public int age;
	
	public TestKey(){}
	
	public TestKey(String name, int age){
		this.name = name;
		this.age = age;
	}

	@Override
	public int hashCode() {
		/*final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());*/
		return age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestKey other = (TestKey) obj;

		if(name.equals(other.name)){
			return true;
		}else{
			return false;
		}
	}
	
	

}
