package com.scrumtrek.simplestore;

public enum PriceCodes {
	Regular {
		@Override
		public double calcAmount(int days) {
			double amount = 2;
			if (days > 2) {
				amount += (days - 2) * 1.5;
			}
			return amount;
		}
	},
	NewRelease {
		@Override
		public double calcAmount(int days) {
			return days * 3;
		}

		@Override
		public int calcPoints(int days) {
			return super.calcPoints(days) + (days > 1? 1: 0);
		}
	},
	Childrens {
		@Override
		public double calcAmount(int days) {
			if (days > 3) {
				return (days - 3) * 1.5;
			} else {
				return 1.5;
			}
		}
	};

	public abstract double calcAmount(int days);

	public int calcPoints(int days) {
		return 0;
	}
}
