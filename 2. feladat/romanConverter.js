function convertToRoman(num) {
  if (typeof num !== 'number' || !Number.isInteger(num)) {
    throw new Error('A bemenetnek egész számnak kell lennie.');
  }

  if (num < 1 || num > 3999) {
    throw new Error('A számnak 1 és 3999 közé kell esnie.');
  }

  const map = [
    { value: 1000, symbol: 'M' },
    { value: 900,  symbol: 'CM' },
    { value: 500,  symbol: 'D' },
    { value: 400,  symbol: 'CD' },
    { value: 100,  symbol: 'C' },
    { value: 90,   symbol: 'XC' },
    { value: 50,   symbol: 'L' },
    { value: 40,   symbol: 'XL' },
    { value: 10,   symbol: 'X' },
    { value: 9,    symbol: 'IX' },
    { value: 5,    symbol: 'V' },
    { value: 4,    symbol: 'IV' },
    { value: 1,    symbol: 'I' }
  ];

  let result = '';
  for (const item of map) {
    while (num >= item.value) {
      result += item.symbol;
      num -= item.value;
    }
  }
  return result;
}