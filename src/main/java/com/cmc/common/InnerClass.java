package com.cmc.common;

/**
 * 实例化内部类
 * 
 * @author HT.LCB
 * @since 2016年11月22日 下午5:40:32
 */
public class InnerClass {

	public static void main(String[] args) {
		// 参见虚拟机class文件代码生成
		new InnerClass().new Inner().new InnerInner().sayHi();
	}

	class Inner {
		public void sayHi() {
			System.out.println("Inner: Hi~");
		}

		class InnerInner {
			public void sayHi() {
				System.out.println("InnerInner: Hi~");
			}
		}
	}

}