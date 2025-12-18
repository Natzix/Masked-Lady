package org.natzi.maskedlady.utils.template;

public class HtmlTemplate {

    static public String templateLoginToken(String username, String uri) {
        return "<body style=\"margin:0; padding:0; background-color:#f7f7f7; font-family:Arial, Helvetica, sans-serif;\">\n" +
                "\n" +
                "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#f7f7f7; padding:40px 0;\">\n" +
                "    <tr>\n" +
                "        <td align=\"center\">\n" +
                "\n" +
                "            <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   style=\"background:#ffffff; border-radius:8px; padding:40px;\n" +
                "                          border:1px solid #e0e0e0; box-shadow:0 4px 8px rgba(0, 0, 0, 0.1);\">\n" +
                "\n" +
                "                <!-- Título -->\n" +
                "                <tr>\n" +
                "                    <td style=\"font-size:28px; font-weight:bold; color:#5c6bc0;\n" +
                "                               text-align:center; padding-bottom:20px;\n" +
                "                               font-family:Georgia, 'Times New Roman', serif;\">\n" +
                "                        NATZI\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- Separador -->\n" +
                "                <tr>\n" +
                "                    <td style=\"border-bottom:1px solid #e0e0e0; padding-bottom:30px;\"></td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- Subtítulo -->\n" +
                "                <tr>\n" +
                "                    <td style=\"font-size:22px; font-weight:bold; color:#4a4a4a;\n" +
                "                               padding-top:20px; padding-bottom:15px; text-align:center;\">\n" +
                "                        Inicio de sesion\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- Contenido -->\n" +
                "                <tr>\n" +
                "                    <td style=\"font-size:16px; line-height:22px; color:#616161; padding-bottom:25px;\">\n" +
                "                        Hola <strong style=\"color:#5c6bc0;\">" + username + "</strong>,<br><br>\n" +
                "\n" +
                "                        Se generó un enlace seguro para que puedas <strong>iniciar sesión</strong> en tu cuenta.<br><br>\n" +
                "\n" +
                "                        Este enlace es <strong>de un solo uso</strong> y estará disponible únicamente durante\n" +
                "                        <strong>5 minutos</strong> por motivos de seguridad.<br><br>\n" +
                "\n" +
                "                        Si <strong>no solicitaste este inicio de sesión</strong>, puedes ignorar este mensaje.\n" +
                "                        No se realizará ningún acceso a tu cuenta.\n" +
                "\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- Enlace de validación -->\n" +
                "                <tr>\n" +
                "                    <td style=\"font-size:16px; line-height:22px; color:#616161;\n" +
                "                               text-align:center; padding-top:20px;\">\n" +
                "                        <a href=\""+ uri + "\"\n" +
                "                           style=\"color:#5c6bc0; font-weight:bold; text-decoration:none;\">\n" +
                "                            Haz clic aquí para iniciar sesión\n" +
                "                        </a>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <!-- Footer -->\n" +
                "                <tr>\n" +
                "                    <td style=\"font-size:12px; color:#9e9e9e; line-height:18px;\n" +
                "                               text-align:center; padding-top:30px;\">\n" +
                "                        Este correo fue generado automáticamente. No respondas a este mensaje.<br>\n" +
                "                        Si tienes dudas, por favor contacta a\n" +
                "                        <a href=\"mailto:support@tuempresa.com\"\n" +
                "                           style=\"color:#5c6bc0; text-decoration:none;\">\n" +
                "                            support@tuempresa.com\n" +
                "                        </a>.\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "            </table>\n" +
                "\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>";
    }

    public static String templateValidationEmail(String username, String uri) {
        return "<body style=\"margin:0; padding:0; background-color:#f7f7f7; font-family:Arial, Helvetica, sans-serif;\">\n" +
                "\n" +
                "    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background-color:#f7f7f7; padding:40px 0;\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\">\n" +
                "\n" +
                "                <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#ffffff; border-radius:8px; padding:40px; border:1px solid #e0e0e0; box-shadow:0 4px 8px rgba(0, 0, 0, 0.1);\">\n" +
                "\n" +
                "                    <!-- Título -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"font-size:28px; font-weight:bold; color:#5c6bc0; text-align:center; padding-bottom:20px; font-family:Georgia, 'Times New Roman', serif;\">\n" +
                "                            <span style=\"color:#5c6bc0;\">NATZI</span>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <!-- Separador -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"border-bottom:1px solid #e0e0e0; padding-bottom:30px;\"></td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <!-- Subtítulo -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"font-size:22px; font-weight:bold; color:#4a4a4a; padding-top:20px; padding-bottom:15px; text-align:center;\">\n" +
                "                            Verificación de correo electronico\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <!-- Contenido -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"font-size:16px; line-height:22px; color:#616161; padding-bottom:25px;\">\n" +
                "                            Hola <strong style=\"color:#5c6bc0;\">" + username + "</strong>,<br><br>\n" +
                "                            Se genero tu enlace para la verificacion de tu correo electronico. Recuerda que solo dispones de " +
                "                               <strong>3 minutos</strong> antes de que expire por motivos de seguridad.<br><br>\n" +
                "                            Si no solicitaste este ott, puedes ignorar este mensaje. Tu seguridad es nuestra prioridad.\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <!-- Enlace de validación -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"font-size:16px; line-height:22px; color:#616161; text-align:center; padding-top:20px;\">\n" +
                "                            <a href=\"" + uri + "\"\n" +
                "                            style=\"color:#5c6bc0; font-weight:bold; text-decoration:none;\">haz clic aquí</a>.\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <!-- Footer -->\n" +
                "                    <tr>\n" +
                "                        <td style=\"font-size:12px; color:#9e9e9e; line-height:18px; text-align:center; padding-top:30px;\">\n" +
                "                            Este correo fue generado automáticamente. No respondas a este mensaje.<br>\n" +
                "                            Si tienes dudas, por favor contacta a <a href=\"mailto:support@tuempresa.com\" style=\"color:#5c6bc0; text-decoration:none;\">support@tuempresa.com</a>.\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                </table>\n" +
                "\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "</body>";
    }


}
