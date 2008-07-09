package net.jcreate.e3.table.theme;

public interface ThemeFactoryBuilder {
  public ThemeFactory build(String pTheme) throws ThemeException;
}
