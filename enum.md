enum  enumSample{
		sample("SAMPLE");
		
		private final String value;
    //构造方法
		Method(String value) {
			this.value = value;
		}
    
		@Override
		public String toString() {
			return this.value;
		}
	}
	
	//待完善 
