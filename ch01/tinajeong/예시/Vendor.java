package ch01.tinajeong.예시;

public enum Vendor {
	NAVER("naver"),
	KAKAO("kakao"),
	ALADIN("aladin");

	private final String description;

    Vendor(String description) {
        this.description = description;
    }
    
	public String getDescription() {
		return this.description;
	}
}
