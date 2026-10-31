QUnit.module('Római szám konverter', function() {

  QUnit.test('1, 2 és 3 átalakítása', function(assert) {
    assert.strictEqual(convertToRoman(1), 'I');
    assert.strictEqual(convertToRoman(2), 'II');
    assert.strictEqual(convertToRoman(3), 'III');
  });

  QUnit.test('5 és 10 átalakítása', function(assert) {
    assert.strictEqual(convertToRoman(5), 'V');
    assert.strictEqual(convertToRoman(10), 'X');
  });

  QUnit.test('Kivonásos szabályok: 4 és 9', function(assert) {
    assert.strictEqual(convertToRoman(4), 'IV');
    assert.strictEqual(convertToRoman(9), 'IX');
  });

  QUnit.test('Összetett számok és feladványbeli példák', function(assert) {
    assert.strictEqual(convertToRoman(14), 'XIV');
    assert.strictEqual(convertToRoman(58), 'LVIII');
    assert.strictEqual(convertToRoman(1994), 'MCMXCIV');
    assert.strictEqual(convertToRoman(3999), 'MMMCMXCIX');
  });

  QUnit.test('Érvénytelen bemenetek kezelése', function(assert) {
    assert.throws(function() { convertToRoman(0); }, /1 és 3999/);
    assert.throws(function() { convertToRoman(-5); }, /1 és 3999/);
    assert.throws(function() { convertToRoman(4000); }, /1 és 3999/);
    assert.throws(function() { convertToRoman(3.14); }, /egész számnak/);
    assert.throws(function() { convertToRoman("10"); }, /egész számnak/);
  });

});